/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
public class TestResultFileBean {
private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
