package com.theandrey.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@RunWith(Categories.class)
@Categories.IncludeCategory({Major.class})
public interface Major {
}
