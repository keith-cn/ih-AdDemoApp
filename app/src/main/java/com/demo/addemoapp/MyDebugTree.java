package com.demo.addemoapp;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public final class MyDebugTree extends Timber.DebugTree {
    @Override
    protected String createStackElementTag(@NotNull StackTraceElement element) {
        return String.format(
                "[L:%s] [M:%s] [C:%s]",
                element.getLineNumber(),
                element.getMethodName(),
                super.createStackElementTag(element));
    }
}
