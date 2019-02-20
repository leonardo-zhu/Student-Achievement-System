package entity;

import java.math.BigDecimal;

public class Student {
    private String id;
    private Integer sid;
    private String student_name;
    private Integer mathScore;
    private Integer EnglishScore;
    private Integer ChineseScore;
    private Double average;
    private Integer total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getMathScore() {
        return mathScore;
    }

    public void setMathScore(Integer mathScore) {
        this.mathScore = mathScore;
    }

    public Integer getEnglishScore() {
        return EnglishScore;
    }

    public void setEnglishScore(Integer englishScore) {
        EnglishScore = englishScore;
    }

    public Integer getChineseScore() {
        return ChineseScore;
    }

    public void setChineseScore(Integer chineseScore) {
        ChineseScore = chineseScore;
    }

    public Double getAverage() {
        return new BigDecimal((getMathScore() + getEnglishScore() + getChineseScore()) / 3).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setAverage() {
        this.average = new BigDecimal((getMathScore() + getEnglishScore() + getChineseScore()) / 3).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setTotal() {
        this.total = getMathScore() + getEnglishScore() + getChineseScore();
    }

    public Double getTotal() {
        return Double.valueOf(getMathScore() + getEnglishScore() + getChineseScore());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", sid=" + sid +
                ", student_name='" + student_name + '\'' +
                ", mathScore=" + mathScore +
                ", EnglishScore=" + EnglishScore +
                ", ChineseScore=" + ChineseScore +
                ", total=" + total +
                '}';
    }
}
