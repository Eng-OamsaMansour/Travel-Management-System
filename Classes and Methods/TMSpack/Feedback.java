package TMSpack;

public class Feedback {
    private String feedback_text;
	private int feedback_ID;

    public Feedback(String feedback_text,int feedback_ID){
      this.feedback_ID=feedback_ID;
      this.feedback_text=feedback_text;

    }
    public int getFeedbackId() {
        return feedback_ID;
      }
      public void setFeedbackId(int feedback_ID) {
        this.feedback_ID = feedback_ID;
      }
      public String getFeedbackText() {
        return feedback_text;
      }

      public void setFeedbackText(String feedback_text) {
        this.feedback_text = feedback_text;
      }


    
}
