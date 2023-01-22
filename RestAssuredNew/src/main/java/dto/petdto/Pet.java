
package dto.petdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    private Category mCategory;
    private Long mId;
    private String mName;
    private List<String> mPhotoUrls;
    private String mStatus;
    private List<Tag> mTags;

}
