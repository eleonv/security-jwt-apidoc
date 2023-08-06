package com.example.security04.util;

public class Constante {
    public static final String RESPONSE_OK = "1";
    public static final String RESPONSE_ERROR = "0";

    public static final String ROL_ADMIN = "ROL_ADMIN";
    public static final String ROL_USER = "ROL_USER";

    // milisegundos(1000ms) * segundos (60s) * minutos(60m) * horas (xh)
    // 1000 * 60 * 60 * X = x horas
    public static final Long JWT_TIEMPO_EXPIRACION_MS = 1000*60*60*1L;

    public static final String JWT_SECRET_KEY = "80ZYbN4fGwsw16hUtHWjs6DMKIbctgMf80ZYbN4fGwsw16hUtHWjs6DMKIbctgMf80ZYbN4fGwsw16hUtHWjs6DMKIbctgMf80";
}