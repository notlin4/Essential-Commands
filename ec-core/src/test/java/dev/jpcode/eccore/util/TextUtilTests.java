package dev.jpcode.eccore.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TextUtil")
public class TextUtilTests {
    @Test
    @DisplayName("flattenRoot output is shaped correctly")
    void flattenRoot_flattensCorrectly()
    {
        var baseStyle = Style.EMPTY.withColor(Formatting.AQUA);
        var input = Text.literal("testing").setStyle(baseStyle)
            .append("token2")
            .append("token3");

        var output = TextUtil.flattenRoot(input);

        assertEquals(output.getFirst().getContent(), input.getContent());
        assertEquals(output.getFirst().getStyle(), baseStyle);

        var inputSiblings = input.getSiblings();
        for (int i = 1; i < output.size(); i++) {
            var inputToken = inputSiblings.get(i - 1);
            var outToken = output.get(i);

            assertEquals(inputToken.getContent(), outToken.getContent());
            assertEquals(inputToken.getStyle(), outToken.getStyle());
        }
    }

    @Test
    @DisplayName("from-to json is remotely sane")
    void fromToJson_isSane()
    {
        var nullJsonStr = Text.Serialization.toJsonString(Text.literal(" hi there! "), DynamicRegistryManager.EMPTY);
        var parsedStyleFromNull = Text.Serialization.fromJson(nullJsonStr, DynamicRegistryManager.EMPTY);

        assert parsedStyleFromNull == null;
    }
}
