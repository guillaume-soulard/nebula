package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.NumberList;
import com.nebula.object.annotation.type.NumberRange;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import static com.nebula.core.NebulaGenerationTypes.number;

public class NumberListTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        NumberList numberList = (NumberList) typeAnnotationOfField;
        return number().among()
                .items(toBigDecimals(numberList.value()));
    }

    private BigDecimal[] toBigDecimals(double[] doubles) {
        BigDecimal[] decimals = new BigDecimal[doubles.length];
        int i = 0;
        for (double dbl : doubles) {
            decimals[i] = new BigDecimal(dbl);
            i++;
        }

        return decimals;
    }
}
