package randombits.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Alexey Raichev
 * @see NistFactory
 * @see randombits.controller.NistController
 * @since 15.04.2019
 * Convert string path variable from the url to NistFactory.
 */
@Component
public class StringToNistTestConverter implements Converter<String, NistFactory> {

    @Override
    public NistFactory convert(String s) {
        return NistFactory.valueOf(s.toUpperCase());
    }
}
