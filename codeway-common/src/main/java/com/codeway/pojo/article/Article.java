package com.codeway.pojo.article;

import com.codeway.enums.ArticleAuditStatus;
import com.codeway.enums.ArticleOriginStatus;
import com.codeway.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "article", description = "文章类")
@Entity
@ToString
@Table(name = "ar_article",
		indexes = {
				@Index(name = "article_keywords", columnList = "keywords"),
				@Index(name = "article_title", columnList = "title"),
				@Index(name = "article_create_at", columnList = "createAt")})
public class Article extends BasePojo implements Serializable {


	@ApiModelProperty(value = "文章分类")
	@JoinColumn(name = "category_id",foreignKey=@ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Category category;

	@ApiModelProperty(value = "推荐阅读", example = "1")
	@Transient
	@JsonIgnoreProperties("related")
	private List<Article> related;

	@ApiModelProperty(value = "分类id",example = "1")
	@Transient
	private String categoryId;

	@ApiModelProperty(value = "标签id",example = "1")
	@Transient
	private String tagsId;

	@ApiModelProperty("用户名")
	@Transient
	private String userName;

	@JoinTable(
			name = "ar_article_tags",
			joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT)))
	@ManyToMany
	private Set<Tag> tags = new HashSet<>();

	@ApiModelProperty("ID")
	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false, length = 20)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.codeway.config.IdGeneratorConfig")
	private String id;

	@ApiModelProperty("用户ID")
	@Column(length = 20)
	private String userId;

	@ApiModelProperty("标题")
	@NotNull(message = "标题不能为空")
	@Column(length = 50)
	private String title;

	@ApiModelProperty("文章封面")
	@Column(length = 200)
	private String thumb;

	@ApiModelProperty(value = "是否公开", example = "1")
	@Column(length = 1)
	private Boolean isPublic;

	@ApiModelProperty(value = "是否置顶", example = "1")
	@Column(length = 1)
	private Boolean isTop;

	@ApiModelProperty(value = "浏览量",example = "1")
	@Column(length = 5)
	private Integer visits;

	@ApiModelProperty(value = "点赞数",example = "1")
	@Column(length = 5)
	private Integer upvote;

	@ApiModelProperty("评论数")
	@Column(length = 5)
	private Integer comment;

	@ApiModelProperty(value = "审核状态", example = "1")
	@Column(length = 1)
	private ArticleAuditStatus reviewState;

	@ApiModelProperty("URL")
	@Column(length = 200)
	private String url;

	@ApiModelProperty("热度")
	@Column(precision = 2, scale = 1,length = 5)
	private float importance;

	@ApiModelProperty("文章描述（概述）")
	@NotNull(message = "概述不能为空")
	@Column(length = 500)
	private String description;

	@ApiModelProperty("关键字")
	@Column(length = 200)
	private String keywords;

	@ApiModelProperty("来源")
	@NotNull(message = "来源不能为空")
	@Column(length = 1)
	private ArticleOriginStatus origin;

	@ApiModelProperty("文章正文")
	@NotNull(message = "内容不能为空")
	@Lob
	@Column(columnDefinition = "text")
	private String content;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Article)) return false;
		Article article = (Article) o;
		return Float.compare(article.importance, importance) == 0 &&
				Objects.equals(userName, article.userName) &&
				id.equals(article.id) &&
				Objects.equals(userId, article.userId) &&
				Objects.equals(title, article.title) &&
				Objects.equals(thumb, article.thumb) &&
				Objects.equals(isPublic, article.isPublic) &&
				Objects.equals(isTop, article.isTop) &&
				Objects.equals(visits, article.visits) &&
				Objects.equals(upvote, article.upvote) &&
				Objects.equals(comment, article.comment) &&
				Objects.equals(reviewState, article.reviewState) &&
				Objects.equals(url, article.url) &&
				Objects.equals(description, article.description) &&
				Objects.equals(keywords, article.keywords) &&
				Objects.equals(origin, article.origin) &&
				Objects.equals(content, article.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, id, userId, title, thumb, isPublic, isTop, visits, upvote, comment, reviewState, url, importance, description, keywords, origin, content);
	}

}