package features.domain;

import junit.framework.Assert;
import features.domain.queries.Query;

public class InheritanceBTest extends AbstractFeaturesTest {

    public void testSaveBottom() {
        InheritanceBBottom b = new InheritanceBBottom();
        b.setName("1");
        b.setMiddleName("2");
        b.setBottomName("3");
        this.commitAndReOpen();

        b = Query.inheritanceBBottom.find(b.getId().intValue());
        Assert.assertEquals("1", b.getName());
        Assert.assertEquals("2", b.getMiddleName());
        Assert.assertEquals("3", b.getBottomName());
    }

}
