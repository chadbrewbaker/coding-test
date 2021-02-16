# Code Test

Base repo for setting up to run tests on a machine.  It is meant to be followed up with a push of code to test after the system has been setup.



-- notes on comparing the JVM UTF-8 decoder with others --



The JVM runs way faster when you get rid of unnessisary heap. For [AWS Lambda](https://docs.aws.amazon.com/lambda/latest/dg/runtimes-modify.html) 

```bash
# JAVA_TOOL_OPTIONS environment variable on AWS Lambda

/usr/bin/time -v java -cp target/scala-2.12/hello-scala.jar coreutils.WC UTF_8_test.txt


/usr/bin/time -v java -Xms1m -cp target/hello-scala.jar coreutils.WC UTF_8_test.txt

```


[Chrome UTF-8 Decoder](https://github.com/v8/v8/blob/master/src/strings/unicode-decoder.cc)

[Chrome UTF-8 DFA ](https://github.com/v8/v8/blob/master/src/third_party/utf8-decoder/utf8-decoder.h)

[Rust UTF-8 Decoder/validator](https://github.com/rust-lang/rust/blob/master/library/core/src/str/validations.rs#L115)



[SpiderMonkey Unicode](https://github.com/mozilla-spidermonkey/rust-frontend/search?q=HB_UNICODE_GENERAL_CATEGORY_SPACING_MARK) seems to be using [harfbuzz](https://harfbuzz.github.io/) heavily.  [Harfbuzz unicode classification](https://github.com/harfbuzz/harfbuzz/blob/master/src/hb-unicode.h) seems to be the most up to date.


[Open JDK UTF8](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/sun/nio/cs/UTF_8.java)


GNU/BSD wc are using 

```c
#include <locale.h>
#include <wchar.h> 

mbstate_t state;

//expands characters from narow to wide format based on locale
size_t mbrtowc( wchar_t *restrict pwc, const char *restrict s, size_t n,
                mbstate_t *restrict ps );
```

[glibc mbrtowc](https://github.com/bminor/glibc/blob/master/wcsmbs/mbrtowc.c)

[latest Apple libc](https://opensource.apple.com/source/Libc/Libc-1439.40.11/include/wchar.h.auto.html)
[which calls a locale specific mbrtowc](https://opensource.apple.com/source/Libc/Libc-1439.40.11/locale/FreeBSD/mbrtowc.c.auto.html)

[.Net Runtime UTF8](https://github.com/dotnet/runtime/blob/master/src/libraries/System.Private.CoreLib/src/System/Text/UTF8Encoding.cs)  on Windows 10 the path for the debug binary of the C library is C:\WINDOWS\system32\ucrtbased.dll.

