package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ---------- letterGrade ----------
    @Test void letterGrade_95_isA()  { assertEquals("A", calc.letterGrade(95)); }
    @Test void letterGrade_85_isB()  { assertEquals("B", calc.letterGrade(85)); }
    @Test void letterGrade_75_isC()  { assertEquals("C", calc.letterGrade(75)); }
    @Test void letterGrade_65_isD()  { assertEquals("D", calc.letterGrade(65)); }
    @Test void letterGrade_30_isF()  { assertEquals("F", calc.letterGrade(30)); }

    @Test void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }
    @Test void letterGrade_above100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ---------- gpa ----------
    @Test void gpa_95_85_is3_5() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001);
    }
    @Test void gpa_mixedCDF_is1_0() {   // C, D, F (default) салаа
        assertEquals(1.0, calc.gpa(new double[]{75, 65, 30}), 0.001);
    }
    @Test void gpa_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }
    @Test void gpa_empty_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    // ---------- scholarship ----------
    @Test void scholarship_3_9_16_noViolation_is100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }
    @Test void scholarship_violation_isZero() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }
    @Test void scholarship_gpaHigh_credits14_is70() {   // credits>=15 F
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }
    @Test void scholarship_gpa3_5_credits13_is70() {    // gpa>=3.8 F, 70 true
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }
    @Test void scholarship_gpa3_5_credits10_is50() {    // credits>=12 F
        assertEquals(50, calc.scholarship(3.5, 10, false));
    }
    @Test void scholarship_gpa3_1_credits16_is50() {    // gpa>=3.4 F
        assertEquals(50, calc.scholarship(3.1, 16, false));
    }
    @Test void scholarship_lowGpa_isZero() {
        assertEquals(0, calc.scholarship(2.5, 16, false));
    }
    @Test void scholarship_gpaBelowZero_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }
    @Test void scholarship_gpaAboveFour_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }
    @Test void scholarship_negativeCredits_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

    // ---------- Хэсэг C: gpa() давталтын 0 / 1 / 2+ дүрэм ----------
    // 0 удаа: gpa_empty_throws, gpa_null_throws (Хэсэг B-д бий)
    @Test void gpa_singleScore_95_is4_0() {                 // 1 удаа
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }
    @Test void gpa_fourScores_95_85_75_65_is2_5() {         // 2+ удаа
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }

    // ---------- Хэсэг D: letterGrade зааг утгууд ----------
    @Test void letterGrade_boundary_0_isF()       { assertEquals("F", calc.letterGrade(0)); }
    @Test void letterGrade_boundary_59_99_isF()   { assertEquals("F", calc.letterGrade(59.99)); }
    @Test void letterGrade_boundary_60_isD()      { assertEquals("D", calc.letterGrade(60)); }
    @Test void letterGrade_boundary_69_99_isD()   { assertEquals("D", calc.letterGrade(69.99)); }
    @Test void letterGrade_boundary_89_99_isB()   { assertEquals("B", calc.letterGrade(89.99)); }
    @Test void letterGrade_boundary_90_isA()      { assertEquals("A", calc.letterGrade(90)); }
    @Test void letterGrade_boundary_100_isA()     { assertEquals("A", calc.letterGrade(100)); }
}
