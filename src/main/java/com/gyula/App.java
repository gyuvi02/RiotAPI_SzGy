package com.gyula;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws IOException, RiotApiException {
        String key = null;
        Path file = FileSystems.getDefault().getPath("ApiKey.txt");
                Charset charset = Charset.forName("US-ASCII");
        System.out.println("Melyik játékost keresed?");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();

        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            String line = null;
            if ((line = reader.readLine()) != null) {
                key = line;
            }
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        ApiConfig config = new ApiConfig().setKey(key);
        RiotApi api = new RiotApi(config);

        try {
            Summoner summoner = api.getSummonerByName(Platform.EUNE, userName);
            System.out.println("Name: " + summoner.getName());
            System.out.println("Level: " + summoner.getSummonerLevel());
            System.out.println("ID: " + summoner.getId());

        } catch (RiotApiException e) {
            System.out.println(e.getMessage());
        }

    }
}
