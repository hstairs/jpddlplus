/*********************************************************************
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 *********************************************************************/

/*********************************************************************
 * Description: Part of the PPMaJaL library
 *             
 * Author: Enrico Scala 2013
 * Contact: enricos83@gmail.com
 *
 *********************************************************************/ 

package wrapped_planners;

import java.io.File;

/**
 *
 * @author enrico
 */
public class Utility {

    public static void deleteFile(String fileName) {
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