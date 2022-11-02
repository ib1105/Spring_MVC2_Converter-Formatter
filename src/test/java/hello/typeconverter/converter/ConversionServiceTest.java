package hello.typeconverter.converter;
import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import static org.assertj.core.api.Assertions.*;
public class ConversionServiceTest {
    @Test
    void conversionService() {
        //등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
/*        Converter<T,S> StringToIpPortConverter = new StringToIpPortConverter();
        conversionService.addConverter(StringToIpPortConverter);*/

        //ex StringToIpPortConverter 이 클래스는 Converter<> 부모클래스를 상속 받았기 떄문에 Converter<> 의 자식메서드이므로
        //conversionService.addConverter 를 사용 할 수 있다.

        //사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");
    }
}