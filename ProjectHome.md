# Qwiki-Wiki #

Qwiki-Wiki is a program designed to help users generate wiki pages from large amounts of data stored in MySQL databases. This program was originally created for use with [Venipedia](http://www.venipedia.org) but could also potentially work with other sites.

## Structure ##

This application has two parts: a Java application that resides on the user's computer, and a handful of PHP scripts that rest on the server. When the user wishes to create a large number of wiki pages from the database, the application enables the user to do so without overwriting portions of pages that other people or groups may want included.