package randombits.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Alexey Raichev
 * @see NistFactory
 * @see randombits.controller.MdController
 * @since 15.04.2019
 * Convert string path variable from the url to MdFactory.
 */
@Component
public class StringToMdTestConverter implements Converter<String, MdFactory> {

    @Override
    public MdFactory convert(String s) {
        return MdFactory.valueOf(s.toUpperCase());
    }
}
