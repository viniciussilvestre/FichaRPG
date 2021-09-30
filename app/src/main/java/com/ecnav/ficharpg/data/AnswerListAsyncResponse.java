package com.ecnav.ficharpg.data;

import com.ecnav.ficharpg.model.ClassFeatures;
import com.ecnav.ficharpg.model.Spell;

import java.util.ArrayList;

public interface AnswerListAsyncResponse
{
    void processFinished(ArrayList<ClassFeatures> classFeaturesArrayList);
}
