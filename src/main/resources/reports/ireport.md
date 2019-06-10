## Band Types
Following there is a brief descriptions of the available bands.

Title
The title band is the first visible band. It is created only once and can be printed on a separate page. Regarding the allowed dimensions, it is not possible during design time to exceed the report page height (top and bottom margins are included). If the title is printed on a separate page, this band height is not included in the calculation of the total sum of all band heights, which has to be less than or equal to the page height, as was mentioned previously.

## Page Header
The page header band allows you to define a page header. The height specified during the design phase usually does not change during the creation process (except for the insertion of vertically resizable components, such as a text fields that contain long text and subreports). The page header appears on all printed pages in the same position defined during the design phase. Title and summary bands do not include the page header when printed on a separate page.

## Column Header
The column header band is printed at the beginning of each detail column (The column concept will be explained later in the "Columns" section). Usually, labels containing the column names of a tabular report are inserted in this band.

## Group Header
A report can contains zero or more group bands, which permit the collection of detail records in real groups. A group header is always accompanied by a group footer (both can be independently visible or not) different properties are associated with a group. They determine its behavior from the graphic point of view. It is possible to always force a group header on a new page or in a new column and to print this band on all pages if the bands below it overflow the single page (as a page header, but at group level). It is possible to fix a minimum height required to print a group header: if it exceeds this height, the group header band will be printed on a new page (please note that a value too large for this property can create an infinite loop during printing).

## Group Footer
The group footer band completes a group. Usually it contains fields to view subtotals or separation graphic elements, such as lines.

## Column Footer
The column footer band appears on at the end of every column. Its dimension are not resizable at run time (not even if it contains resizable elements such as subreports or text fields with a variable number of text lines).

## Page Footer
The page footer band appears on every page where there is a page header. Like the column footer, it is not resizable a run time.

## Last Page Footer
If you want to make the last page footer different from the other footers, it is possible to use the special last page footer band. If the band height is 0, it is completely ignored, and the layout established for the common page will also be used for the last page.

## Summary
The summary band allows to insert fields concerning total calculations, means, or whatever you want to insert at the end of the report. In other systems, this band is often named report footer.

## Background
The background band was introduced after insistent requests from many users who wanted to be able to create watermarks and similar effects (such as a frame around the whole page). It can have a maximum height equal to the page height.