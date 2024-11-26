package dev.rndmorris.tfixins.common.commands.arguments.handlers.flag;

import java.util.Iterator;

import net.minecraft.command.ICommandSender;

import dev.rndmorris.tfixins.common.commands.arguments.handlers.IArgumentHandler;

public class FlagHandler implements IFlagArgumentHandler {

    public static final IArgumentHandler INSTANCE = new FlagHandler();

    @Override
    public Object parse(ICommandSender sender, String current, Iterator<String> input) {
        return true;
    }
}
