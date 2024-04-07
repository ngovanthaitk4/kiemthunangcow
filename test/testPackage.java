/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Entity.HoaDonChiTietVER2;
import Entity.Voucher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author khuong
 */
@RunWith(MockitoJUnitRunner.class)
public class testPackage {
    
     @Mock
    private NhanVienDAO nhanVienDAO;

    @Mock
    private HoaDonDAO hoaDonDAO;

    @Mock
    private KhachHangDAO khachHangDAO;

    @Mock
    private HoaDonChiTietDAO hoaDonChiTietDAO;

    @InjectMocks
    private BanHangJFrame banHangJFrame;

  
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        banHangJFrame = new BanHangJFrame();
        nhanVienDAO = mock(NhanVienDAO.class);
        hoaDonDAO = mock(HoaDonDAO.class);
        hoaDonChiTietDAO = mock(HoaDonChiTietDAO.class);
        khachHangDAO = mock(KhachHangDAO.class);

        banHangJFrame.setNhanVienDAO(nhanVienDAO);
        banHangJFrame.setHoaDonDAO(hoaDonDAO);
        banHangJFrame.setHoaDonChiTietDAO(hoaDonChiTietDAO);
        banHangJFrame.setKhachHangDAO(khachHangDAO);
        
    }


    @Test
    public void testBtnTaoHoaDonMoiActionPerformed() throws SQLException {
        when(nhanVienDAO.getIDByUsername(anyString())).thenReturn(1);
        doNothing().when(hoaDonDAO).addData(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt());

        banHangJFrame.btnTaoHoaDonMoiActionPerformed(null);

        verify(hoaDonDAO).addData(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testTblHoaDonMouseClicked() throws SQLException {
        List<HoaDon> hoaDonList = new ArrayList<>();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(1);
        hoaDonList.add(hoaDon);

        when(hoaDonDAO.getAllChuaThanhToan()).thenReturn(hoaDonList);
        when(hoaDonChiTietDAO.getSPCTByHDID(anyInt())).thenReturn(new ArrayList<>());

        banHangJFrame.tblHoaDonMouseClicked(null);

        verify(hoaDonDAO).getAllChuaThanhToan();
        verify(hoaDonChiTietDAO).getSPCTByHDID(anyInt());
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed() throws SQLException {
        when(tblHoaDon.getSelectedRow()).thenReturn(0);
        when(txtSoDienThoaiKH.getText()).thenReturn("0123456789");
        when(khachHangDAO.getAll()).thenReturn(new ArrayList<>());
        when(khachHangDAO.getIDBSoDienThoai(anyString())).thenReturn(1);
        doNothing().when(hoaDonDAO).updateKhachHang(anyInt(), anyInt());

        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        verify(khachHangDAO).getIDBSoDienThoai(anyString());
        verify(hoaDonDAO).updateKhachHang(anyInt(), anyInt());
    }

    @Test
    public void testBtnKiemTraActionPerformed_validVoucher() throws SQLException {
        List<HoaDonChiTietVER2> hoaDonChiTietList = new ArrayList<>();
        HoaDonChiTietVER2 hoaDonChiTiet = new HoaDonChiTietVER2();
        hoaDonChiTietList.add(hoaDonChiTiet);

        when(tblHoaDon.getSelectedRow()).thenReturn(0);
        when(tblHoaDon.getValueAt(anyInt(), anyInt())).thenReturn(1);
        when(hoaDonChiTietDAO.getSPCTByHDID(anyInt())).thenReturn(hoaDonChiTietList);
        when(hoaDonDAO.getAllChuaThanhToan()).thenReturn(new ArrayList<>());
        when(hoaDonChiTietDAO.getSPCTByHDID(anyInt())).thenReturn(hoaDonChiTietList);
        when(hoaDonChiTietDAO.getSPCTByHDID(anyInt())).thenReturn(hoaDonChiTietList);
        when(txtMaGiamGia.getText()).thenReturn("123");

        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify expected interactions
    }
    
     @Test
    public void testBtnTaoHoaDonMoiActionPerformed() throws SQLException {
        // Mock
        when(nhanVienDAO.getIDByUsername(anyString())).thenReturn(1);

        // Test
        banHangJFrame.btnTaoHoaDonMoiActionPerformed(null);

        // Verify
        verify(hoaDonDAO).addData(1, 0, 0, 0, 0, 0, 1);
        verify(hoaDonDAO).getAllChuaThanhToan();
    }

    @Test
    public void testTblHoaDonMouseClicked() throws SQLException {
        // Mock
        List<HoaDon> hoaDonList = new ArrayList<>();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(1);
        hoaDonList.add(hoaDon);
        when(hoaDonDAO.getAllChuaThanhToan()).thenReturn(hoaDonList);
        when(hoaDonDAO.getSPCTByHDID(1)).thenReturn(new ArrayList<>());

        // Test
        banHangJFrame.tblHoaDonMouseClicked(null);

        // Verify
        verify(hoaDonDAO).getSPCTByHDID(1);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_emptyPhoneNumber() {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("");

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verifyNoInteractions(khachHangDAO);
        verifyNoInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_invalidPhoneNumber() {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("123");

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verifyNoInteractions(khachHangDAO);
        verifyNoInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_nonExistingPhoneNumber() throws SQLException {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("0123456789");
        when(khachHangDAO.getAll()).thenReturn(new ArrayList<>());

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verify(khachHangDAO).getAll();
        verify(khachHangDAO, never()).getIDBSoDienThoai(anyString());
        verifyNoInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_existingPhoneNumber() throws SQLException {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("0123456789");
        List<KhachHang> khachHangList = new ArrayList<>();
        KhachHang khachHang = new KhachHang();
        khachHang.setId(1);
        khachHang.setSoDienThoai("0123456789");
        khachHangList.add(khachHang);
        when(khachHangDAO.getAll()).thenReturn(khachHangList);

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verify(khachHangDAO).getIDBSoDienThoai("0123456789");
        verify(hoaDonDAO).updateKhachHang(1, 1);
        verify(hoaDonDAO).getAllChuaThanhToan();
    }

    @Test
    public void testCboThemThongTinKhachHangActionPerformed_selected() {
        // Test
        banHangJFrame.cboThemThongTinKhachHangActionPerformed(null);

        // Verify
        assertTrue(banHangJFrame.txtSoDienThoaiKH.isEnabled());
        assertTrue(banHangJFrame.btnThemSoDienThoaiKH.isEnabled());
    }

    @Test
    public void testCboThemThongTinKhachHangActionPerformed_unselected() {
        // Mock
        banHangJFrame.txtSoDienThoaiKH.setEnabled(true);
        banHangJFrame.btnThemSoDienThoaiKH.setEnabled(true);

        // Test
        banHangJFrame.cboThemThongTinKhachHangActionPerformed(null);

        // Verify
        assertFalse(banHangJFrame.txtSoDienThoaiKH.isEnabled());
        assertFalse(banHangJFrame.btnThemSoDienThoaiKH.isEnabled());
    }

    @Test
    public void testBtnKiemTraActionPerformed_selectedRowNegative() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(-1);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonChiTietDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_invalidVoucher() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("abc");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonChiTietDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_validVoucher() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);
        List<HoaDonChiTietVER2> hoaDonChiTietList = new ArrayList<>();
        when(hoaDonChiTietDAO.getSPCTByHDID(1)).thenReturn(hoaDonChiTietList);
        Voucher voucher = new Voucher();
        voucher.setDiscount(10);
        when(banHangJFrame.findValidVoucher(123)).thenReturn(voucher);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verify(hoaDonChiTietDAO).getSPCTByHDID(1);
        verify(hoaDonDAO).updateDiscount(1, 10);
        verify(hoaDonDAO).getAllChuaThanhToan();
    }
    @Test
    public void testBtnTaoHoaDonMoiActionPerformed_exceptionThrown() throws SQLException {
        // Mock
        when(nhanVienDAO.getIDByUsername(anyString())).thenThrow(new SQLException());

        // Test
        banHangJFrame.btnTaoHoaDonMoiActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonDAO);
    }

    @Test
    public void testTblHoaDonMouseClicked_exceptionThrown() throws SQLException {
        // Mock
        when(hoaDonDAO.getAllChuaThanhToan()).thenThrow(new SQLException());

        // Test
        banHangJFrame.tblHoaDonMouseClicked(null);

        // Verify
        verifyZeroInteractions(hoaDonChiTietDAO);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_existingPhoneNumberNotAdded() throws SQLException {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("0123456789");
        List<KhachHang> khachHangList = new ArrayList<>();
        KhachHang khachHang = new KhachHang();
        khachHang.setId(1);
        khachHang.setSoDienThoai("0123456789");
        khachHangList.add(khachHang);
        when(khachHangDAO.getAll()).thenReturn(khachHangList);
        when(khachHangDAO.getIDBSoDienThoai("0123456789")).thenReturn(0);

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verify(khachHangDAO).getIDBSoDienThoai("0123456789");
        verify(hoaDonDAO, never()).updateKhachHang(anyInt(), anyInt());
        verifyNoMoreInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnThemSoDienThoaiKHActionPerformed_sqlException() throws SQLException {
        // Mock
        banHangJFrame.tblHoaDon.setRowSelectionInterval(0, 0);
        banHangJFrame.txtSoDienThoaiKH.setText("0123456789");
        when(khachHangDAO.getAll()).thenThrow(new SQLException());

        // Test
        banHangJFrame.btnThemSoDienThoaiKHActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_selectedRowNegative() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(-1);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonChiTietDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_invalidVoucher() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("abc");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonChiTietDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_exceptionThrown() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);
        when(hoaDonChiTietDAO.getSPCTByHDID(1)).thenThrow(new SQLException());

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verifyZeroInteractions(hoaDonDAO);
    }

    @Test
    public void testBtnKiemTraActionPerformed_validVoucherNoDiscount() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);
        List<HoaDonChiTietVER2> hoaDonChiTietList = new ArrayList<>();
        when(hoaDonChiTietDAO.getSPCTByHDID(1)).thenReturn(hoaDonChiTietList);
        Voucher voucher = new Voucher();
        when(banHangJFrame.findValidVoucher(123)).thenReturn(voucher);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verify(hoaDonChiTietDAO).getSPCTByHDID(1);
        verify(hoaDonDAO).updateDiscount(1, 0);
        verify(hoaDonDAO).getAllChuaThanhToan();
    }

    @Test
    public void testBtnKiemTraActionPerformed_validVoucherWithDiscount() throws SQLException {
        // Mock
        banHangJFrame.txtMaGiamGia.setText("123");
        when(banHangJFrame.tblHoaDon.getSelectedRow()).thenReturn(0);
        List<HoaDonChiTietVER2> hoaDonChiTietList = new ArrayList<>();
        when(hoaDonChiTietDAO.getSPCTByHDID(1)).thenReturn(hoaDonChiTietList);
        Voucher voucher = new Voucher();
        voucher.setDiscount(10);
        when(banHangJFrame.findValidVoucher(123)).thenReturn(voucher);

        // Test
        banHangJFrame.btnKiemTraActionPerformed(null);

        // Verify
        verify(hoaDonChiTietDAO).getSPCTByHDID(1);
        verify(hoaDonDAO).updateDiscount(1, 10);
        verify(hoaDonDAO).getAllChuaThanhToan();
    }
    
    
    @Test
    public void testCboThemThongTinKhachHangActionPerformed_enabled() {
        // Test
        banHangJFrame.cboThemThongTinKhachHangActionPerformed(null);

        // Verify
        assertTrue(banHangJFrame.txtSoDienThoaiKH.isEnabled());
        assertTrue(banHangJFrame.btnThemSoDienThoaiKH.isEnabled());
    }

    @Test
    public void testCboThemThongTinKhachHangActionPerformed_disabled() {
        // Set up
        banHangJFrame.cboThemThongTinKhachHang.setSelected(false);

        // Test
        banHangJFrame.cboThemThongTinKhachHangActionPerformed(null);

        // Verify
        assertFalse(banHangJFrame.txtSoDienThoaiKH.isEnabled());
        assertFalse(banHangJFrame.btnThemSoDienThoaiKH.isEnabled());
    }
    
}
