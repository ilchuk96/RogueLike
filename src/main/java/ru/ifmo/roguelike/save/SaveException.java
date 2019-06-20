package ru.ifmo.roguelike.save;

/**
 * Save game exception, throws when any exception in save occurred
 */
public class SaveException extends Exception {
    public SaveException(Exception e) {
    }
}
