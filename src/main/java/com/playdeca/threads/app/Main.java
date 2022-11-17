/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.playdeca.threads.app;

import com.playdeca.threads.counters.Executor;

/**
 *
 * @author alvaro@playdeca.com
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Executor Test = new Executor();
        long bigNumber = 100000000l;
        Test.Execute("DIRTY", 12, 10, bigNumber);
        Test.Execute("VOLATILE", 12, 10, bigNumber);
        Test.Execute("SYNCHRONIZED", 12, 10, bigNumber);
        Test.Execute("RWLOCK", 12, 10, bigNumber);
        Test.Execute("ATOMIC", 12, 10, bigNumber);
        Test.Execute("ADDER", 12, 10, bigNumber);
    }

}
