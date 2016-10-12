/****************************************************************************/
/*  File:       DirEntry.java                                               */
/*  Author:     F. Georges - H2O Consulting                                 */
/*  Date:       2009-12-21                                                  */
/*  Tags:                                                                   */
/*      Copyright (c) 2009 Florent Georges (see end of file.)               */
/* ------------------------------------------------------------------------ */


package org.expath.zip;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.expath.zip.Entry.Path;

/**
 * TODO: ...
 *
 * @author Florent Georges
 */
public class DirEntry
        extends Entry
{
    public DirEntry(URI src, Path path)
    {
        super(path.getPath(true));
        mySrc  = src;
        myPath = path;
    }

    public boolean isDir()
    {
        return true;
    }

    @Override
    protected void doSerialize(ZipOutputStream out, ZipEntry entry)
            throws ZipException, IOException
    {
        // create a dir entry, to take empty dirs into account
        out.putNextEntry(entry);
        if ( mySrc != null ) {
            // TODO: Ensure the resolution is done against the right Base URI!
            File d = new File(mySrc);
            if ( ! d.exists() ) {
                throw new ZipException("Directory does not exist: " + d);
            }
            if ( ! d.isDirectory() ) {
                throw new ZipException("Not a directory: " + d);
            }
            for ( File f : d.listFiles() ) {
                Entry e = makeEntry(myPath, mySrc, f);
                e.serialize(out);
                myPath.pop();
            }
        }
    }

    private URI mySrc;
    private Path myPath;
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
