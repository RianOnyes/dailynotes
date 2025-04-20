package com.example.dailynotes;

public class Connections {
//    Base Url
    String urlNotes = "http://192.168.1.7/cobaapi/api/";
    String urlAuth =  "http://192.168.1.7/cobaapi/auth/";

//    Endpoints
    String getNotes = urlNotes + "getnotes.php";
    String addNote = urlNotes + "addnote.php";
    String updateNote = urlNotes + "updatenote.php";
    String deleteNote = urlNotes + "deletenote.php";

    String login = urlAuth + "login.php";
    String register = urlAuth + "register.php";
}

// http://192.168.1.4/cobaapi/api/getnotes.php

