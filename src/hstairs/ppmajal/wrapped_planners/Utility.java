/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.wrapped_planners;

import java.io.File;

/**
 * @author enrico
 */
public class Utility {

    public static void deleteFile (String fileName) {
        // A File object to represent the filename
        File f = new File(fileName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            return;
        }
        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + fileName);
        }

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new IllegalArgumentException(
                        "Delete: directory not empty: " + fileName);
            }
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }
}
