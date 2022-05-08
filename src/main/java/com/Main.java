package com;

import com.controller.DatabaseController;
import com.model.*;
import com.testPackage.Client;
import com.testPackage.Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        myServer.main();
        MyClient client = new MyClient();
        client.main();



    }
}