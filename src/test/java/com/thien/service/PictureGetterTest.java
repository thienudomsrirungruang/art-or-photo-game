package com.thien.service;

import com.thien.entity.PictureInfo;
import com.thien.repository.PictureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PictureGetterTest {

    @Mock
    PictureRepository pr;

    @Mock
    Random random;

    @Mock
    PictureInfo pi;

    @InjectMocks
    PictureGetter pictureGetter;

    @Test
    public void getRandomPictureTest(){
        when(pr.findAll()).thenReturn(new ArrayList<PictureInfo>(Arrays.asList(pi)));
        pictureGetter.getRandomPicture();
        verify(pr, times(1)).findAll();
        verify(random, times(1)).nextInt(any(Integer.class));
    }

    @Test
    public void emptyResultTest(){
        when(pr.findAll()).thenReturn(new ArrayList<PictureInfo>());
        PictureInfo output = pictureGetter.getRandomPicture();
        assertNull(output);
        verify(pr, times(1)).findAll();
        verify(random, times(0)).nextInt();
    }

    @Test
    public void nullResultTest(){
        when(pr.findAll()).thenReturn(null);
        PictureInfo output = pictureGetter.getRandomPicture();
        assertNull(output);
        verify(pr, times(1)).findAll();
        verify(random, times(0)).nextInt();
    }

}
