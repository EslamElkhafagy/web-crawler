package com.boost.webcrawler.model;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
/**
 * Response model for crawler node
 */
@ApiModel(description = "Response model for crawler node")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-27T01:10:07.064+02:00")

public class ApiModelCrawlerNode  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("url")
    private String url = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("nodes")
    private List<ApiModelCrawlerNode> nodes = null;

    public ApiModelCrawlerNode url(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApiModelCrawlerNode title(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ApiModelCrawlerNode nodes(List<ApiModelCrawlerNode> nodes) {
        this.nodes = nodes;
        return this;
    }

    public ApiModelCrawlerNode addNodesItem(ApiModelCrawlerNode nodesItem) {
        if (this.nodes == null) {
            this.nodes = new ArrayList<>();
        }
        this.nodes.add(nodesItem);
        return this;
    }

    public List<ApiModelCrawlerNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ApiModelCrawlerNode> nodes) {
        this.nodes = nodes;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiModelCrawlerNode crawlerNode = (ApiModelCrawlerNode) o;
        return Objects.equals(this.url, crawlerNode.url) &&
                Objects.equals(this.title, crawlerNode.title) &&
                Objects.equals(this.nodes, crawlerNode.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title, nodes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiModelCrawlerNode {\n");

        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

