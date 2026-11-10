# lab09-jacoco

## Оюутны мэдээлэл

- Нэр: **Д.Нурсолтан**
- Оюутны код: **B232270039**

## Хөгжүүлэлтийн орчин

`java -version` гаралт:

```text
openjdk version "21.0.12.1" 2026-08-18
OpenJDK Runtime Environment (build 21.0.12.1+1-1-deb13u1)
OpenJDK 64-Bit Server VM (build 21.0.12.1+1-1-deb13u1, mixed mode, sharing)
```

`mvn -version` гаралт:

```text
Apache Maven 3.9.9
Maven home: /usr/share/maven
Java version: 21.0.12.1, vendor: Debian, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.12.113+deb13-amd64", arch: "amd64", family: "unix"
```
## Хэсэг A: Хамралтын ажиглалт

5 happy-path тестээр Line хамралт XX%, Branch хамралт XX% гарсан (results/jacoco-A.csv).
Шар ромбо бүхий мөрүүд нь оролтын шалгалтын `||` нөхцөлүүд (`score < 0 || score > 100`, `scores == null || length == 0`, `gpa < 0 || gpa > 4 || credits < 0`), `hasViolation`, `score >= 70`, `gpa >= 3.8 && credits >= 15` бөгөөд эдгээрт зөвхөн нэг тал нь (хэвийн урсгал) туссан тул салаа хагас хамрагдсан.
Улаан мөрүүд нь бүх `IllegalArgumentException` throw мөр, сахилгын зөрчилтэй (0%) тохиолдол, letterGrade-ийн D/F, gpa-ийн C/D/default case болон тэтгэлгийн 70%/50%/0 замууд бөгөөд алдааны болон хязгаарын замууд хүрээгүй байна.
