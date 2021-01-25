package todo;

public class Task {

    private String content;
    private boolean completed;

    public Task(String content, boolean completed) {
        this.content = content;
        this.completed = completed;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    public void editContent(String newContent) {
        content = newContent;
    }
}
