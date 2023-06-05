package com.goit.hibernate.app.servlet;

public class HttpCode {

    public static final int CONTINUE = 100;
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NOT_AUTHORITATIVE = 203;
    public static final int NO_CONTENT = 204;
    public static final int RESET = 205;
    public static final int PARTIAL = 206;
    public static final int MULT_CHOICE = 300;
    public static final int MOVED_PERM = 301;
    public static final int MOVED_TEMP = 302;
    public static final int SEE_OTHER = 303;
    public static final int NOT_MODIFIED = 304;
    public static final int USE_PROXY = 305;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PAYMENT_REQUIRED = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int BAD_METHOD = 405;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int PROXY_AUTH = 407;
    public static final int CLIENT_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int GONE = 410;
    public static final int LENGTH_REQUIRED = 411;
    public static final int PRECON_FAILED = 412;
    public static final int ENTITY_TOO_LARGE = 413;
    public static final int REQ_TOO_LONG = 414;
    public static final int UNSUPPORTED_TYPE = 415;
    public static final int INTERNAL_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int BAD_GATEWAY = 502;
    public static final int UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
    public static final int VERSION = 505;

    public static String msg(int code) {

        switch (code) {
            case OK:
                return " OK";
            case CONTINUE:
                return " Continue";
            case CREATED:
                return " Created";
            case ACCEPTED:
                return " Accepted";
            case NOT_AUTHORITATIVE:
                return " Non-Authoritative Information";
            case NO_CONTENT:
                return " No Content";
            case RESET:
                return " Reset Content";
            case PARTIAL:
                return " Partial Content";
            case MULT_CHOICE:
                return " Multiple Choices";
            case MOVED_PERM:
                return " Moved Permanently";
            case MOVED_TEMP:
                return " Temporary Redirect";
            case SEE_OTHER:
                return " See Other";
            case NOT_MODIFIED:
                return " Not Modified";
            case USE_PROXY:
                return " Use Proxy";
            case BAD_REQUEST:
                return " Bad Request";
            case UNAUTHORIZED:
                return " Unauthorized";
            case PAYMENT_REQUIRED:
                return " Payment Required";
            case FORBIDDEN:
                return " Forbidden";
            case NOT_FOUND:
                return " Not Found";
            case BAD_METHOD:
                return " Method Not Allowed";
            case NOT_ACCEPTABLE:
                return " Not Acceptable";
            case PROXY_AUTH:
                return " Proxy Authentication Required";
            case CLIENT_TIMEOUT:
                return " Request Time-Out";
            case CONFLICT:
                return " Conflict";
            case GONE:
                return " Gone";
            case LENGTH_REQUIRED:
                return " Length Required";
            case PRECON_FAILED:
                return " Precondition Failed";
            case ENTITY_TOO_LARGE:
                return " Request Entity Too Large";
            case REQ_TOO_LONG:
                return " Request-URI Too Large";
            case UNSUPPORTED_TYPE:
                return " Unsupported Media Type";
            case INTERNAL_ERROR:
                return " Internal Server Error";
            case NOT_IMPLEMENTED:
                return " Not Implemented";
            case BAD_GATEWAY:
                return " Bad Gateway";
            case UNAVAILABLE:
                return " Service Unavailable";
            case GATEWAY_TIMEOUT:
                return " Gateway Timeout";
            case VERSION:
                return " HTTP Version Not Supported";
            default:
                return " ";
        }
    }
}
