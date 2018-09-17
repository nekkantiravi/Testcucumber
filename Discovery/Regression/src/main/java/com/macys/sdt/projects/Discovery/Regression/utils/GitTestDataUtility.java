package com.macys.sdt.projects.Discovery.Regression.utils;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/1/2017.
 */
public class GitTestDataUtility {
    public static String repoPath;


    private static final Logger logger = LoggerFactory.getLogger(GitTestDataUtility.class);

    private static GitTestDataUtility instance;

    //private constructor to avoid client applications to use constructor
    private GitTestDataUtility() throws IOException {

    }

    public static GitTestDataUtility getInstance() throws IOException {
        if(instance == null){
            repoPath=fetchRepoData();
            instance = new GitTestDataUtility();
        }
        return instance;
    }

    private static String fetchRepoData() throws IOException {
        //variables
        String remotePath;
        Git git = null;
        remotePath = "https://code.devops.fds.com/YH03402/Preview_TestData.git";
        //creating temp file
        File workingDirectory = File.createTempFile("git_repo_testdata", "");
        workingDirectory.delete();
        workingDirectory.mkdir();
        CloneCommand cloneCmd = git.cloneRepository();
        try {
            cloneCmd.setURI(remotePath)
                    .setDirectory(workingDirectory)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        logger.info("Temp File Path::" + workingDirectory.getAbsoluteFile());
        return workingDirectory.getAbsoluteFile().toString();


    }


}
