package pl.meeting.meetingapp;

public final class ApiRoutes {

    public static class Base {
        public static final String PATH = "/api/v1";
    }

    public static class User {
        public static final String PATH = "/users";
        public static final String PROFILE = "/profile";
        public static final String ID = "/{userId}";
        public static final String AUTH = "/auth";
        public static final String LOGIN = "/login";
        public static final String USERS = "/users";
    }

    public static class Event {
        public static final String PATH = "/event";
    }


}
