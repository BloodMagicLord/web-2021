package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.itmo.web.lesson4.util.Color.randomColor;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", randomColor()),
            new User(6, "pashka", "Pavel Mavrin", randomColor()),
            new User(9, "geranazarov555", "Georgiy Nazarov", randomColor()),
            new User(11, "tourist", "Gennady Korotkevich",  randomColor())
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
