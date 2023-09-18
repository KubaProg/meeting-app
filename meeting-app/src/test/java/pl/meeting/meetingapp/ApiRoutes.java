package pl.meeting.meetingapp;

public final class ApiRoutes {

    public static class Base {
        public static final String PATH = "/api/v1";
    }

    public static class User {
        public static final String PATH = "/email";
        public static final String ID = "/{id}";
        public static final String AUTH = "/auth";
        public static final String LOGIN = "/login";
        public static final String USERS = "/users";


    }

    public static class Profile {
        public static final String USER_ID = "/{userId}";
        public static final String PATH = "/profiles";
    }

}
