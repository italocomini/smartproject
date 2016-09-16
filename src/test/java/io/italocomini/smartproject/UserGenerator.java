package io.italocomini.smartproject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String[][] users = {
                {"1", "mickey", "abcd01", "Mickey", "Mouse", "no"},
                {"2", "minnie", "abcd01", "Minnie", "Mouse", "no"},
                {"3", "donald", "abcd01", "Donald", "Duck", "no"},
                {"4", "daisy", "abcd01", "Daisy", "Duck", "no"},
                {"5", "clarabelle", "abcd01", "Clarabelle", "Cow", "no"},
                {"6", "admin", "admin", "Super", "Admin", "yes"}
        };

        String sql = "insert into users (id, username, password, first_name, last_name, admin) values (%s, '%s', '%s', '%s', '%s', '%s');";

        for(String[] user : users) {

            System.out.println(String.format(sql, user[0], user[1], passwordEncoder.encode(user[2]) , user[3], user[4], user[5]));

        }

    }

}