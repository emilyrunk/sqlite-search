# sqlite-search
Android SearchView example using SQLite
## Purpose
Purpose of this repo is to demonstrate how to use a search widget that searches through SQLite database which has been
created here in Python

##Google Documentation
[Search Overview](https://developer.android.com/guide/topics/search/index.html)
[Creating Search Interface/Widget](https://developer.android.com/guide/topics/search/search-dialog.html)

##General Steps
1. Create Searchable Configuration XML.
2. Create Searchable Activity.
3. Declare searchable activity appropriately in AndroidManifest.xml
4. Receive query via Intent.
5. Search thru data.
6. Present the results.
### Searchable Dialog or Searchable Widget
Searchable Dialog | Searchable Widget
--- | ---
Placed at top of Activity | Can be placed Anywhere including top of activity
Hiden by default | Usually used as action view in App bar
Up to Android 3.0 | Android 3.0 and Higher
