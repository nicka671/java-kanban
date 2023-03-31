package objects;

import enums.Status;

public class Subtask extends Task
{
   private Status current_status = Status.NEW;

   public void setEpicsId(int epicsId) {
      this.epicsId = epicsId;
   }

   public int getEpicsId() {
      return epicsId;
   }

   protected int epicsId;
}
