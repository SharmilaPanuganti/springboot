package cgg.springassign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long categoryId;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "category_description")
  private String categoryDescription;

  @Column(name = "category_created_by")
  private String categoryCreatedBy;

  @Column(name = "category_creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date categoryCreationDate;

  @OneToMany(
    mappedBy = "category",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @JsonIgnore
  private List<Note> notes;

  // No-arg constructor
  public Category() {
    this.categoryCreationDate = new Date();
  }

  // Parameterized constructor
  public Category(
    String categoryName,
    String categoryDescription,
    String categoryCreatedBy
  ) {
    this.categoryName = categoryName;
    this.categoryDescription = categoryDescription;
    this.categoryCreatedBy = categoryCreatedBy;
    this.categoryCreationDate = new Date();
  }

  // Getters and Setters

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryDescription() {
    return categoryDescription;
  }

  public void setCategoryDescription(String categoryDescription) {
    this.categoryDescription = categoryDescription;
  }

  public String getCategoryCreatedBy() {
    return categoryCreatedBy;
  }

  public void setCategoryCreatedBy(String categoryCreatedBy) {
    this.categoryCreatedBy = categoryCreatedBy;
  }

  public Date getCategoryCreationDate() {
    return categoryCreationDate;
  }

  public void setCategoryCreationDate(Date categoryCreationDate) {
    this.categoryCreationDate = categoryCreationDate;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return (
      "Category{" +
      "categoryId=" +
      categoryId +
      ", categoryName='" +
      categoryName +
      '\'' +
      ", categoryDescription='" +
      categoryDescription +
      '\'' +
      ", categoryCreatedBy='" +
      categoryCreatedBy +
      '\'' +
      ", categoryCreationDate=" +
      categoryCreationDate +
      ", notes=" +
      notes +
      '}'
    );
  }
}
