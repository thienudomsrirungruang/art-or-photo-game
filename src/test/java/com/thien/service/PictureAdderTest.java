package com.thien.service;

import com.thien.entity.PictureInfo;
import com.thien.repository.PictureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PictureAdderTest {

    @Mock
    PictureRepository pr;

    @InjectMocks
    PictureAdder pictureAdder;

    @Test
    public void uploadImageTest(){
        when(pr.save(Matchers.any(PictureInfo.class))).thenReturn(null);
        pictureAdder.uploadImage("", true);
        verify(pr, times(1)).save(any(PictureInfo.class));
    }
}