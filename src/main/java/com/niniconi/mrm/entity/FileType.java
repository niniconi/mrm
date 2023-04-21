package com.niniconi.mrm.entity;

import java.util.HashMap;

public abstract class FileType {
    public static final String TYPE_APPLICATION_ANDREW_INSET  = "application/andrew-inset";
    public static final String TYPE_APPLICATION_JSON      = "application/json";
    public static final String TYPE_APPLICATION_ZIP       = "application/zip";
    public static final String TYPE_APPLICATION_X_GZIP      = "application/x-gzip";
    public static final String TYPE_APPLICATION_TGZ       = "application/tgz";
    public static final String TYPE_APPLICATION_MSWORD      = "application/msword";
    public static final String TYPE_APPLICATION_POSTSCRIPT    = "application/postscript";
    public static final String TYPE_APPLICATION_PDF       = "application/pdf";
    public static final String TYPE_APPLICATION_JNLP      = "application/jnlp";
    public static final String TYPE_APPLICATION_MAC_BINHEX40  = "application/mac-binhex40";
    public static final String TYPE_APPLICATION_MAC_COMPACTPRO  = "application/mac-compactpro";
    public static final String TYPE_APPLICATION_MATHML_XML    = "application/mathml+xml";
    public static final String TYPE_APPLICATION_OCTET_STREAM  = "application/octet-stream";
    public static final String TYPE_APPLICATION_ODA       = "application/oda";
    public static final String TYPE_APPLICATION_RDF_XML     = "application/rdf+xml";
    public static final String TYPE_APPLICATION_JAVA_ARCHIVE  = "application/java-archive";
    public static final String TYPE_APPLICATION_RDF_SMIL    = "application/smil";
    public static final String TYPE_APPLICATION_SRGS      = "application/srgs";
    public static final String TYPE_APPLICATION_SRGS_XML    = "application/srgs+xml";
    public static final String TYPE_APPLICATION_VND_MIF     = "application/vnd.mif";
    public static final String TYPE_APPLICATION_VND_MSEXCEL   = "application/vnd.ms-excel";
    public static final String TYPE_APPLICATION_VND_MSPOWERPOINT= "application/vnd.ms-powerpoint";
    public static final String TYPE_APPLICATION_VND_RNREALMEDIA = "application/vnd.rn-realmedia";
    public static final String TYPE_APPLICATION_X_BCPIO     = "application/x-bcpio";
    public static final String TYPE_APPLICATION_X_CDLINK    = "application/x-cdlink";
    public static final String TYPE_APPLICATION_X_CHESS_PGN   = "application/x-chess-pgn";
    public static final String TYPE_APPLICATION_X_CPIO      = "application/x-cpio";
    public static final String TYPE_APPLICATION_X_CSH     = "application/x-csh";
    public static final String TYPE_APPLICATION_X_DIRECTOR    = "application/x-director";
    public static final String TYPE_APPLICATION_X_DVI     = "application/x-dvi";
    public static final String TYPE_APPLICATION_X_FUTURESPLASH  = "application/x-futuresplash";
    public static final String TYPE_APPLICATION_X_GTAR      = "application/x-gtar";
    public static final String TYPE_APPLICATION_X_HDF     = "application/x-hdf";
    public static final String TYPE_APPLICATION_X_JAVASCRIPT  = "application/x-javascript";
    public static final String TYPE_APPLICATION_X_KOAN      = "application/x-koan";
    public static final String TYPE_APPLICATION_X_LATEX     = "application/x-latex";
    public static final String TYPE_APPLICATION_X_NETCDF    = "application/x-netcdf";
    public static final String TYPE_APPLICATION_X_OGG     = "application/x-ogg";
    public static final String TYPE_APPLICATION_X_SH      = "application/x-sh";
    public static final String TYPE_APPLICATION_X_SHAR      = "application/x-shar";
    public static final String TYPE_APPLICATION_X_SHOCKWAVE_FLASH = "application/x-shockwave-flash";
    public static final String TYPE_APPLICATION_X_STUFFIT     = "application/x-stuffit";
    public static final String TYPE_APPLICATION_X_SV4CPIO     = "application/x-sv4cpio";
    public static final String TYPE_APPLICATION_X_SV4CRC    = "application/x-sv4crc";
    public static final String TYPE_APPLICATION_X_TAR       = "application/x-tar";
    public static final String TYPE_APPLICATION_X_RAR_COMPRESSED= "application/x-rar-compressed";
    public static final String TYPE_APPLICATION_X_TCL       = "application/x-tcl";
    public static final String TYPE_APPLICATION_X_TEX       = "application/x-tex";
    public static final String TYPE_APPLICATION_X_TEXINFO   = "application/x-texinfo";
    public static final String TYPE_APPLICATION_X_TROFF     = "application/x-troff";
    public static final String TYPE_APPLICATION_X_TROFF_MAN   = "application/x-troff-man";
    public static final String TYPE_APPLICATION_X_TROFF_ME    = "application/x-troff-me";
    public static final String TYPE_APPLICATION_X_TROFF_MS    = "application/x-troff-ms";
    public static final String TYPE_APPLICATION_X_USTAR     = "application/x-ustar";
    public static final String TYPE_APPLICATION_X_WAIS_SOURCE = "application/x-wais-source";
    public static final String TYPE_APPLICATION_VND_MOZZILLA_XUL_XML = "application/vnd.mozilla.xul+xml";
    public static final String TYPE_APPLICATION_XHTML_XML     = "application/xhtml+xml";
    public static final String TYPE_APPLICATION_XSLT_XML    = "application/xslt+xml";
    public static final String TYPE_APPLICATION_XML       = "application/xml";
    public static final String TYPE_APPLICATION_XML_DTD     = "application/xml-dtd";
    public static final String TYPE_IMAGE_BMP         = "image/bmp";
    public static final String TYPE_IMAGE_CGM         = "image/cgm";
    public static final String TYPE_IMAGE_GIF         = "image/gif";
    public static final String TYPE_IMAGE_IEF         = "image/ief";
    public static final String TYPE_IMAGE_JPEG          = "image/jpeg";
    public static final String TYPE_IMAGE_TIFF          = "image/tiff";
    public static final String TYPE_IMAGE_PNG         = "image/png";
    public static final String TYPE_IMAGE_SVG_XML       = "image/svg+xml";
    public static final String TYPE_IMAGE_VND_DJVU        = "image/vnd.djvu";
    public static final String TYPE_IMAGE_WAP_WBMP        = "image/vnd.wap.wbmp";
    public static final String TYPE_IMAGE_X_CMU_RASTER      = "image/x-cmu-raster";
    public static final String TYPE_IMAGE_X_ICON        = "image/x-icon";
    public static final String TYPE_IMAGE_X_PORTABLE_ANYMAP   = "image/x-portable-anymap";
    public static final String TYPE_IMAGE_X_PORTABLE_BITMAP   = "image/x-portable-bitmap";
    public static final String TYPE_IMAGE_X_PORTABLE_GRAYMAP  = "image/x-portable-graymap";
    public static final String TYPE_IMAGE_X_PORTABLE_PIXMAP   = "image/x-portable-pixmap";
    public static final String TYPE_IMAGE_X_RGB         = "image/x-rgb";
    public static final String TYPE_AUDIO_BASIC         = "audio/basic";
    public static final String TYPE_AUDIO_MIDI          = "audio/midi";
    public static final String TYPE_AUDIO_MPEG          = "audio/mpeg";
    public static final String TYPE_AUDIO_X_AIFF        = "audio/x-aiff";
    public static final String TYPE_AUDIO_X_MPEGURL       = "audio/x-mpegurl";
    public static final String TYPE_AUDIO_X_PN_REALAUDIO    = "audio/x-pn-realaudio";
    public static final String TYPE_AUDIO_X_WAV         = "audio/x-wav";
    public static final String TYPE_CHEMICAL_X_PDB        = "chemical/x-pdb";
    public static final String TYPE_CHEMICAL_X_XYZ        = "chemical/x-xyz";
    public static final String TYPE_MODEL_IGES          = "model/iges";
    public static final String TYPE_MODEL_MESH          = "model/mesh";
    public static final String TYPE_MODEL_VRLM          = "model/vrml";
    public static final String TYPE_TEXT_PLAIN          = "text/plain";
    public static final String TYPE_TEXT_RICHTEXT       = "text/richtext";
    public static final String TYPE_TEXT_RTF          = "text/rtf";
    public static final String TYPE_TEXT_HTML         = "text/html";
    public static final String TYPE_TEXT_CALENDAR       = "text/calendar";
    public static final String TYPE_TEXT_CSS          = "text/css";
    public static final String TYPE_TEXT_SGML         = "text/sgml";
    public static final String TYPE_TEXT_TAB_SEPARATED_VALUES = "text/tab-separated-values";
    public static final String TYPE_TEXT_VND_WAP_XML      = "text/vnd.wap.wml";
    public static final String TYPE_TEXT_VND_WAP_WMLSCRIPT    = "text/vnd.wap.wmlscript";
    public static final String TYPE_TEXT_X_SETEXT       = "text/x-setext";
    public static final String TYPE_TEXT_X_COMPONENT      = "text/x-component";
    public static final String TYPE_VIDEO_QUICKTIME       = "video/quicktime";
    public static final String TYPE_VIDEO_MPEG          = "video/mpeg";
    public static final String TYPE_VIDEO_VND_MPEGURL     = "video/vnd.mpegurl";
    public static final String TYPE_VIDEO_X_MSVIDEO       = "video/x-msvideo";
    public static final String TYPE_VIDEO_X_MS_WMV        = "video/x-ms-wmv";
    public static final String TYPE_VIDEO_X_SGI_MOVIE     = "video/x-sgi-movie";
    public static final String TYPE_X_CONFERENCE_X_COOLTALK   = "x-conference/x-cooltalk";
    private static HashMap<String, String> TypeMapping;

    static {
        TypeMapping = new HashMap<String, String>(200) {
            private void put1(String key, String value) {
                if (put(key, value) != null) {
                    throw new IllegalArgumentException("Duplicated extension: " + key);
                }
            }
            {
                put1("xul", TYPE_APPLICATION_VND_MOZZILLA_XUL_XML);
                put1("json", TYPE_APPLICATION_JSON);
                put1("ice", TYPE_X_CONFERENCE_X_COOLTALK);
                put1("movie", TYPE_VIDEO_X_SGI_MOVIE);
                put1("avi", TYPE_VIDEO_X_MSVIDEO);
                put1("wmv", TYPE_VIDEO_X_MS_WMV);
                put1("m4u", TYPE_VIDEO_VND_MPEGURL);
                put1("mxu", TYPE_VIDEO_VND_MPEGURL);
                put1("htc", TYPE_TEXT_X_COMPONENT);
                put1("etx", TYPE_TEXT_X_SETEXT);
                put1("wmls", TYPE_TEXT_VND_WAP_WMLSCRIPT);
                put1("wml", TYPE_TEXT_VND_WAP_XML);
                put1("tsv", TYPE_TEXT_TAB_SEPARATED_VALUES);
                put1("sgm", TYPE_TEXT_SGML);
                put1("sgml", TYPE_TEXT_SGML);
                put1("css", TYPE_TEXT_CSS);
                put1("ifb", TYPE_TEXT_CALENDAR);
                put1("ics", TYPE_TEXT_CALENDAR);
                put1("wrl", TYPE_MODEL_VRLM);
                put1("vrlm", TYPE_MODEL_VRLM);
                put1("silo", TYPE_MODEL_MESH);
                put1("mesh", TYPE_MODEL_MESH);
                put1("msh", TYPE_MODEL_MESH);
                put1("iges", TYPE_MODEL_IGES);
                put1("igs", TYPE_MODEL_IGES);
                put1("rgb", TYPE_IMAGE_X_RGB);
                put1("ppm", TYPE_IMAGE_X_PORTABLE_PIXMAP);
                put1("pgm", TYPE_IMAGE_X_PORTABLE_GRAYMAP);
                put1("pbm", TYPE_IMAGE_X_PORTABLE_BITMAP);
                put1("pnm", TYPE_IMAGE_X_PORTABLE_ANYMAP);
                put1("ico", TYPE_IMAGE_X_ICON);
                put1("ras", TYPE_IMAGE_X_CMU_RASTER);
                put1("wbmp", TYPE_IMAGE_WAP_WBMP);
                put1("djv", TYPE_IMAGE_VND_DJVU);
                put1("djvu", TYPE_IMAGE_VND_DJVU);
                put1("svg", TYPE_IMAGE_SVG_XML);
                put1("ief", TYPE_IMAGE_IEF);
                put1("cgm", TYPE_IMAGE_CGM);
                put1("bmp", TYPE_IMAGE_BMP);
                put1("xyz", TYPE_CHEMICAL_X_XYZ);
                put1("pdb", TYPE_CHEMICAL_X_PDB);
                put1("ra", TYPE_AUDIO_X_PN_REALAUDIO);
                put1("ram", TYPE_AUDIO_X_PN_REALAUDIO);
                put1("m3u", TYPE_AUDIO_X_MPEGURL);
                put1("aifc", TYPE_AUDIO_X_AIFF);
                put1("aif", TYPE_AUDIO_X_AIFF);
                put1("aiff", TYPE_AUDIO_X_AIFF);
                put1("mp3", TYPE_AUDIO_MPEG);
                put1("mp2", TYPE_AUDIO_MPEG);
                put1("mp1", TYPE_AUDIO_MPEG);
                put1("mpga", TYPE_AUDIO_MPEG);
                put1("kar", TYPE_AUDIO_MIDI);
                put1("mid", TYPE_AUDIO_MIDI);
                put1("midi", TYPE_AUDIO_MIDI);
                put1("dtd", TYPE_APPLICATION_XML_DTD);
                put1("xsl", TYPE_APPLICATION_XML);
                put1("xml", TYPE_APPLICATION_XML);
                put1("xslt", TYPE_APPLICATION_XSLT_XML);
                put1("xht", TYPE_APPLICATION_XHTML_XML);
                put1("xhtml", TYPE_APPLICATION_XHTML_XML);
                put1("src", TYPE_APPLICATION_X_WAIS_SOURCE);
                put1("ustar", TYPE_APPLICATION_X_USTAR);
                put1("ms", TYPE_APPLICATION_X_TROFF_MS);
                put1("me", TYPE_APPLICATION_X_TROFF_ME);
                put1("man", TYPE_APPLICATION_X_TROFF_MAN);
                put1("roff", TYPE_APPLICATION_X_TROFF);
                put1("tr", TYPE_APPLICATION_X_TROFF);
                put1("t", TYPE_APPLICATION_X_TROFF);
                put1("texi", TYPE_APPLICATION_X_TEXINFO);
                put1("texinfo", TYPE_APPLICATION_X_TEXINFO);
                put1("tex", TYPE_APPLICATION_X_TEX);
                put1("tcl", TYPE_APPLICATION_X_TCL);
                put1("sv4crc", TYPE_APPLICATION_X_SV4CRC);
                put1("sv4cpio", TYPE_APPLICATION_X_SV4CPIO);
                put1("sit", TYPE_APPLICATION_X_STUFFIT);
                put1("swf", TYPE_APPLICATION_X_SHOCKWAVE_FLASH);
                put1("shar", TYPE_APPLICATION_X_SHAR);
                put1("sh", TYPE_APPLICATION_X_SH);
                put1("cdf", TYPE_APPLICATION_X_NETCDF);
                put1("nc", TYPE_APPLICATION_X_NETCDF);
                put1("latex", TYPE_APPLICATION_X_LATEX);
                put1("skm", TYPE_APPLICATION_X_KOAN);
                put1("skt", TYPE_APPLICATION_X_KOAN);
                put1("skd", TYPE_APPLICATION_X_KOAN);
                put1("skp", TYPE_APPLICATION_X_KOAN);
                put1("js", TYPE_APPLICATION_X_JAVASCRIPT);
                put1("hdf", TYPE_APPLICATION_X_HDF);
                put1("gtar", TYPE_APPLICATION_X_GTAR);
                put1("spl", TYPE_APPLICATION_X_FUTURESPLASH);
                put1("dvi", TYPE_APPLICATION_X_DVI);
                put1("dxr", TYPE_APPLICATION_X_DIRECTOR);
                put1("dir", TYPE_APPLICATION_X_DIRECTOR);
                put1("dcr", TYPE_APPLICATION_X_DIRECTOR);
                put1("csh", TYPE_APPLICATION_X_CSH);
                put1("cpio", TYPE_APPLICATION_X_CPIO);
                put1("pgn", TYPE_APPLICATION_X_CHESS_PGN);
                put1("vcd", TYPE_APPLICATION_X_CDLINK);
                put1("bcpio", TYPE_APPLICATION_X_BCPIO);
                put1("rm", TYPE_APPLICATION_VND_RNREALMEDIA);
                put1("ppt", TYPE_APPLICATION_VND_MSPOWERPOINT);
                put1("mif", TYPE_APPLICATION_VND_MIF);
                put1("grxml", TYPE_APPLICATION_SRGS_XML);
                put1("gram", TYPE_APPLICATION_SRGS);
                put1("smil", TYPE_APPLICATION_RDF_SMIL);
                put1("smi", TYPE_APPLICATION_RDF_SMIL);
                put1("rdf", TYPE_APPLICATION_RDF_XML);
                put1("ogg", TYPE_APPLICATION_X_OGG);
                put1("oda", TYPE_APPLICATION_ODA);
                put1("dmg", TYPE_APPLICATION_OCTET_STREAM);
                put1("lzh", TYPE_APPLICATION_OCTET_STREAM);
                put1("so", TYPE_APPLICATION_OCTET_STREAM);
                put1("lha", TYPE_APPLICATION_OCTET_STREAM);
                put1("dms", TYPE_APPLICATION_OCTET_STREAM);
                put1("bin", TYPE_APPLICATION_OCTET_STREAM);
                put1("mathml", TYPE_APPLICATION_MATHML_XML);
                put1("cpt", TYPE_APPLICATION_MAC_COMPACTPRO);
                put1("hqx", TYPE_APPLICATION_MAC_BINHEX40);
                put1("jnlp", TYPE_APPLICATION_JNLP);
                put1("ez", TYPE_APPLICATION_ANDREW_INSET);
                put1("txt", TYPE_TEXT_PLAIN);
                put1("ini", TYPE_TEXT_PLAIN);
                put1("c", TYPE_TEXT_PLAIN);
                put1("h", TYPE_TEXT_PLAIN);
                put1("cpp", TYPE_TEXT_PLAIN);
                put1("cxx", TYPE_TEXT_PLAIN);
                put1("cc", TYPE_TEXT_PLAIN);
                put1("chh", TYPE_TEXT_PLAIN);
                put1("java", TYPE_TEXT_PLAIN);
                put1("csv", TYPE_TEXT_PLAIN);
                put1("bat", TYPE_TEXT_PLAIN);
                put1("cmd", TYPE_TEXT_PLAIN);
                put1("asc", TYPE_TEXT_PLAIN);
                put1("rtf", TYPE_TEXT_RTF);
                put1("rtx", TYPE_TEXT_RICHTEXT);
                put1("html", TYPE_TEXT_HTML);
                put1("htm", TYPE_TEXT_HTML);
                put1("zip", TYPE_APPLICATION_ZIP);
                put1("rar", TYPE_APPLICATION_X_RAR_COMPRESSED);
                put1("gzip", TYPE_APPLICATION_X_GZIP);
                put1("gz", TYPE_APPLICATION_X_GZIP);
                put1("tgz", TYPE_APPLICATION_TGZ);
                put1("tar", TYPE_APPLICATION_X_TAR);
                put1("gif", TYPE_IMAGE_GIF);
                put1("jpeg", TYPE_IMAGE_JPEG);
                put1("jpg", TYPE_IMAGE_JPEG);
                put1("jpe", TYPE_IMAGE_JPEG);
                put1("tiff", TYPE_IMAGE_TIFF);
                put1("tif", TYPE_IMAGE_TIFF);
                put1("png", TYPE_IMAGE_PNG);
                put1("au", TYPE_AUDIO_BASIC);
                put1("snd", TYPE_AUDIO_BASIC);
                put1("wav", TYPE_AUDIO_X_WAV);
                put1("mov", TYPE_VIDEO_QUICKTIME);
                put1("qt", TYPE_VIDEO_QUICKTIME);
                put1("mpeg", TYPE_VIDEO_MPEG);
                put1("mpg", TYPE_VIDEO_MPEG);
                put1("mpe", TYPE_VIDEO_MPEG);
                put1("abs", TYPE_VIDEO_MPEG);
                put1("doc", TYPE_APPLICATION_MSWORD);
                put1("xls", TYPE_APPLICATION_VND_MSEXCEL);
                put1("eps", TYPE_APPLICATION_POSTSCRIPT);
                put1("ai", TYPE_APPLICATION_POSTSCRIPT);
                put1("ps", TYPE_APPLICATION_POSTSCRIPT);
                put1("pdf", TYPE_APPLICATION_PDF);
                put1("exe", TYPE_APPLICATION_OCTET_STREAM);
                put1("dll", TYPE_APPLICATION_OCTET_STREAM);
                put1("class", TYPE_APPLICATION_OCTET_STREAM);
                put1("jar", TYPE_APPLICATION_JAVA_ARCHIVE);
            }
        };
    }
    public static void registerType(String ext, String type) {
        TypeMapping.put(ext, type);
    }

    /**
     * Returns the corresponding type to the given extension.
     * If no type was found it returns 'application/octet-stream' type.
     */
    public static String getType(String ext) {
        String type = lookupType(ext);
        if (type == null) {
            type = TYPE_APPLICATION_OCTET_STREAM;
        }
        return type;
    }

    /**
     * Simply returns type or <code>null</code> if no type is found.
     */
    public static String lookupType(String ext) {
        return TypeMapping.get(ext.toLowerCase());
    }
}
