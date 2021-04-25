package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StudentSynthesizer implements ISynthesizer{

    @Override
    public List<String> SynthetizerProject(Project proyect) {

        List<String> studentsdatos = new ArrayList<>();

        for(Student student: proyect.getMembers())
        {
            String name = student.getName();
            Duration WorkedHours = student.getWorkedHours();

            studentsdatos.add(name+", "+WorkedHours.toDays());
        }
        System.out.println(studentsdatos);
        return studentsdatos;
    }
}
