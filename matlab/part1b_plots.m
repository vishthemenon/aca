clear
clc
close all
% radiVals = [1,2,3,4,5,8,10,15,20,25,30,40,50,75,100,150,200,250];
% radiVals2 = [1,2,4,8,12,16,20,25,30,35,39,45,50,59];
radiVals = 5:5:80;
CLCD = zeros(length(radiVals),2);
CP = cell(length(radiVals),1);
root = 'data/radius_1/';
for i = 1:length(radiVals)
    tempClCd = readmatrix([root,'CLCD',num2str(radiVals(i)),'.csv']);
    CLCD(i,:) = tempClCd(end,2:3);
    CP{i} = readmatrix([root,'CP',num2str(radiVals(i)),'.csv']);
end

figure
subplot(2,1,1);
hold on
plot(radiVals,CLCD(:,1), 'LineWidth',1.5, 'Color','k')
% plot(radiVals2,CLCD(radiVals2,1), 'LineWidth',1, 'Color','r')
hold off
title("C_D")
xlabel("Domain Radius (m)")
ylabel("C_D")
grid on

subplot(2,1,2);
hold on
plot(radiVals,CLCD(:,2), 'LineWidth',1.5, 'Color','k')
% plot(radiVals2,CLCD(radiVals2,2), 'LineWidth',1, 'Color','r')
hold off
title("C_L")
xlabel("Domain Radius (m)")
ylabel("C_L")
ylim([0,1]);
grid on

