package dev.rndmorris.tfixins.common.commands.arguments.handlers.named;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.ICommandSender;

import dev.rndmorris.tfixins.common.commands.arguments.handlers.IArgumentHandler;

public class SearchHandler implements INamedArgumentHandler {

    public static final IArgumentHandler INSTANCE = new SearchHandler();

    @Override
    public Object parse(ICommandSender sender, String current, Iterator<String> args) {
        return buildSearchTerm(args);
    }

    @Override
    public List<String> getAutocompleteOptions(ICommandSender sender, String current, Iterator<String> args) {
        buildSearchTerm(args);
        if (!args.hasNext()) {
            return Collections.emptyList();
        }
        return null;
    }

    private String buildSearchTerm(Iterator<String> args) {
        var insideQuote = false;
        var escapeNext = false;

        final var str = new StringBuilder();

        while (args.hasNext()) {
            if (!insideQuote && str.length() > 0) {
                break;
            }
            if (str.length() > 0) {
                str.append(' ');
            }
            final var currentTerm = args.next();

            for (var index = 0; index < currentTerm.length(); ++index) {
                final var character = currentTerm.charAt(index);
                if (character == '\\') {
                    if (escapeNext) {
                        str.append(character);
                        escapeNext = false;
                        continue;
                    }
                    escapeNext = true;
                    continue;
                }
                if (character == '"') {
                    if (escapeNext) {
                        str.append(character);
                        escapeNext = false;
                        continue;
                    }
                    if (insideQuote) {
                        insideQuote = false;
                        break;
                    }
                    insideQuote = true;
                    continue;
                }
                str.append(character);
            }
        }

        return str.toString();
    }

}
