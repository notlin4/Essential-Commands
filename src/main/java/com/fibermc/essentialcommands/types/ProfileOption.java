package com.fibermc.essentialcommands.types;

import java.util.Optional;

import com.mojang.brigadier.arguments.ArgumentType;

public record ProfileOption<T>(
    ArgumentType<T> argumentType,
    T defaultValue,
    ProfileOptionFromContextSetter<T> profileSetter,
    ProfileOptionGetter<Optional<T>> profileGetter) {}
