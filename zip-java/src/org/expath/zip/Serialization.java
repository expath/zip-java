/****************************************************************************/
/*  File:       Serialization.java                                          */
/*  Author:     F. Georges - H2O Consulting                                 */
/*  Date:       2009-12-21                                                  */
/*  Tags:                                                                   */
/*      Copyright (c) 2009 Florent Georges (see end of file.)               */
/* ------------------------------------------------------------------------ */


package org.expath.zip;

import java.util.Properties;
import javax.xml.transform.OutputKeys;

/**
 * Represent a set of serialization parameters.
 *
 * @author Florent Georges
 */
public class Serialization
{
    /**
     * Return a serialization parameters map usable with JAXP.
     */
    public Properties getOutputOptions()
            throws ZipException
    {
        if ( myMethod == null ) {
            throw new ZipException("The method serialization param is not set");
        }
        return myOptions;
    }

    /**
     * Return the {@code method} serialization param if set, {@code null} otherwise.
     */
    public String getMethod()
    {
        return myMethod;
    }

    /**
     * Set a serialization parameter, given its name and its value.
     */
    public void setOutputParam(String name, String value)
            throws ZipException
    {
        if ( "method".equals(name) ) {
            if ( value != null && ! ("text".equals(value)
                     || "xml".equals(value) || "html".equals(value)
                     || "xhtml".equals(value) || "base64".equals(value)
                     || "hex".equals(value)) ) {
                throw new ZipException("Invalid value for the method serialization param: " + value);
            }
            myMethod = value;
            setProperty(OutputKeys.METHOD, value);
        }
        else if ( "indent".equals(name) ) {
            setProperty(OutputKeys.INDENT, value);
        }
        else if ( "media-type".equals(name) ) {
            setProperty(OutputKeys.MEDIA_TYPE, value);
        }
        // TODO: There is no std constant in JAXP for this.  How to do?  For now
        // supported in SaxonSerialization, but it should be supported here...
        // else if ( "byte-order-mark".equals(name) ) {
        //     setProperty(OutputKeys., value);
        // }
        else if ( "cdata-section-elements".equals(name) ) {
            setProperty(OutputKeys.CDATA_SECTION_ELEMENTS, value);
        }
        else if ( "doctype-public".equals(name) ) {
            setProperty(OutputKeys.DOCTYPE_PUBLIC, value);
        }
        else if ( "doctype-system".equals(name) ) {
            setProperty(OutputKeys.DOCTYPE_SYSTEM, value);
        }
        else if ( "encoding".equals(name) ) {
            setProperty(OutputKeys.ENCODING, value);
        }
        // TODO: There is no std constant in JAXP for this.  How to do?  For now
        // supported in SaxonSerialization, but it should be supported here...
        // else if ( "escape-uri-uttributes".equals(name) ) {
        //     setProperty(OutputKeys., value);
        // }
        // else if ( "normalization-form".equals(name) ) {
        //     setProperty(OutputKeys., value);
        // }
        else if ( "omit-xml-declaration".equals(name) ) {
            setProperty(OutputKeys.OMIT_XML_DECLARATION, value);
        }
        else if ( "standalone".equals(name) ) {
            setProperty(OutputKeys.STANDALONE, value);
        }
        // TODO: There is no std constant in JAXP for this.  How to do?  For now
        // supported in SaxonSerialization, but it should be supported here...
        // else if ( "suppress-indentation".equals(name) ) {
        //     setProperty(OutputKeys., value);
        // }
        // else if ( "undeclare-prefixes".equals(name) ) {
        //     setProperty(OutputKeys., value);
        // }
        else if ( "version".equals(name) ) {
            setProperty(OutputKeys.VERSION, value);
        }
        else {
            throw new ZipException("Unknown serialization paramater: " + name + " (value: " + value + ")");
        }
    }

    protected void setProperty(String prop, String value)
    {
        myOptions.put(prop, value);
    }

    private Properties myOptions = new Properties();
    private String     myMethod  = null;
}


/* ------------------------------------------------------------------------ */
/*  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS COMMENT.               */
/*                                                                          */
/*  The contents of this file are subject to the Mozilla Public License     */
/*  Version 1.0 (the "License"); you may not use this file except in        */
/*  compliance with the License. You may obtain a copy of the License at    */
/*  http://www.mozilla.org/MPL/.                                            */
/*                                                                          */
/*  Software distributed under the License is distributed on an "AS IS"     */
/*  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.  See    */
/*  the License for the specific language governing rights and limitations  */
/*  under the License.                                                      */
/*                                                                          */
/*  The Original Code is: all this file.                                    */
/*                                                                          */
/*  The Initial Developer of the Original Code is Florent Georges.          */
/*                                                                          */
/*  Contributor(s): none.                                                   */
/* ------------------------------------------------------------------------ */
