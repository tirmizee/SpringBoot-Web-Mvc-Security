package com.tirmizee.core.jdbcrepository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;

import com.tirmizee.core.jdbcrepository.sql.SqlGenerator;
import com.tirmizee.core.jdbcrepository.sql.SqlGeneratorFactory;

/**
 * Implementation of {@link PagingAndSortingRepository} using {@link JdbcTemplate}
 */
public abstract class AbstractJdbcRepository<T extends Persistable<ID>, ID extends Serializable> implements JdbcRepository<T, ID>, InitializingBean {

	protected final Log LOG = LogFactory.getLog(getClass());
	
	private TableDescription table;
	private final RowMapper<T> rowMapper;
	private final RowUnmapper<T> rowUnmapper;

	private DataSource dataSource;
	private JdbcOperations jdbcOperations;
	
	private SqlGenerator sqlGenerator;
	private SqlGeneratorFactory sqlGeneratorFactory = SqlGeneratorFactory.getInstance();
	
	public AbstractJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, SqlGenerator sqlGenerator, TableDescription table) {
		Assert.notNull(rowMapper,"row mapper must be provided");
		Assert.notNull(rowUnmapper,"row unmapper must be provided");

		this.rowUnmapper = rowUnmapper;
		this.rowMapper = rowMapper;
		this.sqlGenerator = sqlGenerator;
		this.table = table;
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, TableDescription table) {
		this(rowMapper, rowUnmapper, null, table);
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName, String idColumn) {
		this(rowMapper, rowUnmapper, null, new TableDescription(tableName, idColumn));
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName) {
		this(rowMapper, rowUnmapper, new TableDescription(tableName, "id"));
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, TableDescription table) {
		this(rowMapper, new MissingRowUnmapper<T>(), null, table);
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, String tableName, String idColumn) {
		this(rowMapper, new MissingRowUnmapper<T>(), null, new TableDescription(tableName, idColumn));
	}

	public AbstractJdbcRepository(RowMapper<T> rowMapper, String tableName) {
		this(rowMapper, new MissingRowUnmapper<T>(), new TableDescription(tableName, "id"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(dataSource, "dataSource must be provided");
		this.jdbcOperations = new JdbcTemplate(this.dataSource);
		this.sqlGenerator = sqlGeneratorFactory.getGenerator(dataSource);
	}
	
	public void setJdbcOperations(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	protected void initialDataSource(DataSource dataSource) {
		Assert.notNull(dataSource, "dataSource must be provided");
		this.dataSource = dataSource;
    }

	public TableDescription getTable() {
		return table;
	}
	
	protected JdbcOperations getJdbcOps() {
        return jdbcOperations;
    }
	
	protected SqlGenerator getSqlGenerator() {
		return sqlGenerator;
	}

	@Override
	public long count() {
		return jdbcOperations.queryForObject(sqlGenerator.count(table), Long.class);
	}
	
	public long count(String statement) {
		return jdbcOperations.queryForObject(sqlGenerator.count(statement), Long.class);
	}
	
	public long count(String statement, Object...param) {
		return jdbcOperations.queryForObject(sqlGenerator.count(statement), Long.class, param);
	}

	@Override
	public void delete(ID id) {
		jdbcOperations.update(sqlGenerator.deleteById(table), idToObjectArray(id));
	}

	@Override
	public void delete(T entity) {
		jdbcOperations.update(sqlGenerator.deleteById(table), idToObjectArray(entity.getId()));
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		for (T t : entities) { delete(t); }
	}

	@Override
	public void deleteAll() {
		jdbcOperations.update(sqlGenerator.deleteAll(table));
	}

	public boolean exists(ID id) {
		return jdbcOperations.queryForObject(sqlGenerator.existsById(table), Integer.class, idToObjectArray(id)) > 0;
	}

	@Override
	public List<T> findAll() {
		return jdbcOperations.query(sqlGenerator.selectAll(table), rowMapper);
	}

	@Override
	public T findOne(ID id) {
		final Object[] idColumns = idToObjectArray(id);
		final List<T> entityOrEmpty = jdbcOperations.query(sqlGenerator.selectById(table), idColumns, rowMapper);
		return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
	}

	private static <ID> Object[] idToObjectArray(ID id) {
		return (id instanceof Object[]) ? (Object[]) id : new Object[]{id};
	}

	private static <ID> List<Object> idToObjectList(ID id) {
		if (id instanceof Object[])
			return Arrays.asList((Object[]) id);
		else
			return Collections.<Object>singletonList(id);
	}

	@Override
	public <S extends T> S save(S entity) {
		return entity.isNew() ? create(entity) : update(entity);
	}

	protected <S extends T> S update(S entity) {
		final Map<String, Object> columns = preUpdate(entity, columns(entity));
		final List<Object> idValues = removeIdColumns(columns);
		final List<String> IdColumns = table.getIdColumns();
		final String updateQuery = sqlGenerator.update(table, columns);
		for (int i = 0,size =  table.getIdColumns().size(); i < size; ++i) {
			columns.put(IdColumns.get(i), idValues.get(i));
		}
		final Object[] queryParams = columns.values().toArray();
		jdbcOperations.update(updateQuery, queryParams);
		return postUpdate(entity);
	}

	protected Map<String,Object> preUpdate(T entity, Map<String, Object> columns) {
		return columns;
	}

	protected <S extends T> S create(S entity) {
		final Map<String, Object> columns = preCreate(columns(entity), entity);
		return (entity.getId() == null) ? createWithAutoGeneratedKey(entity, columns)
										: createWithManuallyAssignedKey(entity, columns);
	}

	protected <S extends T> S createWithManuallyAssignedKey(S entity, Map<String, Object> columns) {
		String insertQuery = sqlGenerator.insert(table, columns);
	    Object[] queryParams = columns.values().toArray();
	    jdbcOperations.update(insertQuery, queryParams);
		return postCreate(entity, null);
	}

	protected <S extends T> S createWithAutoGeneratedKey(S entity, Map<String, Object> columns) {
		
		removeIdColumns(columns);
		
		final String createQuery = sqlGenerator.insert(table, columns);
		final Object[] queryParams = columns.values().toArray();
		final GeneratedKeyHolder key = new GeneratedKeyHolder();

		jdbcOperations.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				final String idColumnName = table.getIdColumns().get(0);
				final PreparedStatement ps = con.prepareStatement(createQuery, new String[]{idColumnName});
				for (int i = 0 , length = queryParams.length; i < length; ++i) {
					ps.setObject(i + 1, queryParams[i]);
				}
				return ps;
			}
		}, key);
		return postCreate(entity, key.getKey());
	}

	private List<Object> removeIdColumns(Map<String, Object> columns) {
		List<Object> idColumnsValues = new ArrayList<Object>(columns.size());
		for (String idColumn : table.getIdColumns()) {
			idColumnsValues.add(columns.remove(idColumn));
		}
		return idColumnsValues;
	}

	protected Map<String, Object> preCreate(Map<String, Object> columns, T entity) {
		return columns;
	}

	private LinkedHashMap<String, Object> columns(T entity) {
		return new LinkedHashMap<String, Object>(rowUnmapper.mapColumns(entity));
	}

	protected <S extends T> S postUpdate(S entity) {
		return entity;
	}

	/**
	 * General purpose hook method that is called every time {@link #create} is called with a new entity.
	 * <p/>
	 * OVerride this method e.g. if you want to fetch auto-generated key from database
	 *
	 *
	 * @param entity Entity that was passed to {@link #create}
	 * @param generatedId ID generated during INSERT or NULL if not available/not generated.
	 * todo: Type should be ID, not Number
	 * @return Either the same object as an argument or completely different one
	 */
	protected <S extends T> S postCreate(S entity, Number generatedId) {
		return entity;
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> ret = new ArrayList<S>();
		for (S s : entities) {
			ret.add(save(s));
		}
		return ret;
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		final List<ID> idsList = toList(ids);
		if (idsList.isEmpty()) {
			return Collections.emptyList();
		}
		final Object[] idColumnValues = flatten(idsList);
		return jdbcOperations.query(sqlGenerator.selectByIds(table, idsList.size()), rowMapper, idColumnValues);
	}

	private static <T> List<T> toList(Iterable<T> iterable) {
		final List<T> result = new ArrayList<T>();
		for (T item : iterable) { result.add(item); }
		return result;
	}

	private static <ID> Object[] flatten(List<ID> ids) {
		final List<Object> result = new ArrayList<Object>();
		for (ID id : ids) { result.addAll(idToObjectList(id)); }
		return result.toArray();
	}
	
	public final Object[] params(Object...params){
		return params;
	}

	@Override
	public List<T> findAll(Sort sort) {
		return jdbcOperations.query(sqlGenerator.selectAll(table, sort), rowMapper);
	}

	@Override
	public Page<T> findAll(Pageable page) {
		String query = sqlGenerator.selectAll(table, page);
		return new PageImpl<T>(jdbcOperations.query(query, rowMapper), page, count());
	}

}
