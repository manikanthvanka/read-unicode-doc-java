# read-unicode-doc-java
# Read Docx/Dox file in Java using Apache Tika Library

# About
1. Reads the docx/doc file with the Unicode format
2. Parse the file using AutoDetectParser and ParseContext class of Tika (There are many different parsers offered in the Apache Tika package.)
3. Read into InputStream and set it to OutputStream
4. Convert the outputStream to String
5. Using Java 8 Files API write the text to file
6. Done

# Libraries Used

Apache Tika (ver 1.2) https://tika.apache.org/

# Limitations
Works for only .docx and doc files



