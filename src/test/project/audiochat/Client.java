/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.project.audiochat;

/**
 *
 * @author Bas
 */
import java.io.IOException;

/**
 * Trivial client for the date server.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        new VoiceServer().start();
    }
}
