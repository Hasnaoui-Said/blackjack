package com.blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternIpAddress {
    public static String pattern = "^((2[0-4]\\d|25[0-6]|[01]?\\d{0,2})\\.){3}(2[0-4]\\d|25[0-6]|[01]?\\d{0,2})$";
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int count= Integer.parseInt(s.nextLine());
        List<String> ips=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ips.add(s.next());
        }

        ips.stream().map(ip->ip.matches(pattern)).forEach(System.out::println);

    }
}
