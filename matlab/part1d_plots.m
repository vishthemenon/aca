clear
clc
close all
aoaVals = [0,2,4,6,8];
ClCd = zeros(length(aoaVals),2);

for i = 1:length(aoaVals)
    tempClCd = readmatrix(['../data/part1d_data/ClCd',num2str(aoaVals(i)),'.csv']);
    ClCd(i,:) = tempClCd(end,2:3);
end

figure
plot(aoaVals,ClCd(:,1), 'LineWidth',1.5, 'Color','k')
title("C_D")
xlabel("Angle of Attack (degrees)")
ylabel("C_D")
grid on
saveas(gcf,'plots/part2d_plots/CD_aoa','epsc')


figure
plot(aoaVals,ClCd(:,2), 'LineWidth',1.5, 'Color','k')
title("C_L")
xlabel("Angle of Attack (degrees)")
ylabel("C_L")
grid on
saveas(gcf,'plots/part2d_plots/CL_aoa','epsc')

figure


