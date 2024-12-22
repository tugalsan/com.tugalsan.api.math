package com.tugalsan.api.math.server;

import com.tugalsan.api.function.client.TGS_Func_OutTyped_In2;
import java.util.Objects;

public class TS_MathArrayScalarBooleanExamples {

    public static class FuncByte {

        public static TGS_Func_OutTyped_In2<Boolean, Byte, Byte> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }

    public static class FuncShort {

        public static TGS_Func_OutTyped_In2<Boolean, Short, Short> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }

    public static class FuncInteger {

        public static TGS_Func_OutTyped_In2<Boolean, Integer, Integer> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }

    public static class FuncLong {

        public static TGS_Func_OutTyped_In2<Boolean, Long, Long> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }

    public static class FuncFloat {

        public static TGS_Func_OutTyped_In2<Boolean, Float, Float> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }

    public static class FuncDouble {

        public static TGS_Func_OutTyped_In2<Boolean, Double, Double> func_eq() {
            return (a, b) -> Objects.equals(a, b);
        }
    }
}
