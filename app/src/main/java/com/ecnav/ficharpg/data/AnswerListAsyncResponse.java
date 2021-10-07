package com.ecnav.ficharpg.data;

import com.ecnav.ficharpg.model.Classes;

import java.util.ArrayList;

public interface AnswerListAsyncResponse
{
    void processFinished(ArrayList<Classes> classesArrayList);
}
